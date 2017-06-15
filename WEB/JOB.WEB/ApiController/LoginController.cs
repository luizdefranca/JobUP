using JOB.DATA;
using JOB.DATA.Domain;
using JOB.WEB.Helper;
using JOB.WEB.Models;
using Microsoft.AspNet.Identity.Owin;
using System;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;

namespace JOB.WEB.ApiController
{
    /// <summary>
    /// API exclusiva para lidar com o template MVC de segurança (logins e cadastros de usuarios)
    /// </summary>
    [AllowAnonymous]
    public class LoginController : System.Web.Http.ApiController
    {
        private Contexto ctx = new Contexto();

        public ApplicationSignInManager SignInManager => HttpContext.Current.GetOwinContext().Get<ApplicationSignInManager>();
        public ApplicationUserManager UserManager => HttpContext.Current.GetOwinContext().GetUserManager<ApplicationUserManager>();

        /// <summary>
        /// Realiza o login no sistema
        /// </summary>
        /// <param name="Email">Username do usuario</param>
        /// <param name="Password">Senha do usuario</param>
        /// <returns>
        /// 200 -> login efetuado (retorna a classe usuario);
        /// 403 -> usuario bloqueado;
        /// 412 -> requer verificacao de email;
        /// 400 -> falha no login
        /// </returns>
        public async Task<HttpResponseMessage> Get(string Email, string Password)
        {
            var user = await UserManager.FindByNameAsync(Email);

            var result = await SignInManager.PasswordSignInAsync(Email, Password, false, shouldLockout: true);
            switch (result)
            {
                case SignInStatus.Success:
                    var id = Guid.Parse(user.Id);
                    var usuario = ctx.Usuario.FirstOrDefault(w => w.ID_USUARIO == id);
                    if (usuario == null)
                    {
                        return Request.CreateResponse(HttpStatusCode.OK, id);
                    }

                    return Request.CreateResponse(HttpStatusCode.OK, usuario);

                case SignInStatus.LockedOut:
                    return Request.CreateResponse(HttpStatusCode.Forbidden, new HttpError("LockedOut"));

                case SignInStatus.RequiresVerification:
                    return Request.CreateResponse(HttpStatusCode.PreconditionFailed, new HttpError("RequiresVerification"));

                case SignInStatus.Failure:
                default:
                    return Request.CreateResponse(HttpStatusCode.BadRequest, new HttpError("Failure"));
            }
        }

        /// <summary>
        /// Realiza o cadastro do usuario de login no sistema
        /// </summary>
        /// <param name="Login">Username do usuario</param>
        /// <param name="Email">Email do usuario</param>
        /// <param name="Password">Senha do usuario</param>
        /// <returns>
        /// 200 -> cadastro efetuado (retorna o ID do usuario);
        /// 400 -> falha no cadastro
        /// </returns>
        public async Task<HttpResponseMessage> Get(string Login, string Email, string Password)
        {
            var user = new ApplicationUser { UserName = Login, Email = Email, EmailConfirmed = true };
            var result = await UserManager.CreateAsync(user, Password);
            if (result.Succeeded)
            {
                Guid id = Guid.Parse(user.Id);
                MoedaHelper.Movimentar(ctx, id, 1000, "CADASTRO NO SISTEMA");

                return Request.CreateResponse(HttpStatusCode.OK, user.Id);
            }
            return Request.CreateResponse(HttpStatusCode.BadRequest, result);
        }
    }
}
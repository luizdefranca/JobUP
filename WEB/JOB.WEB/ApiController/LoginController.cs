using JOB.DATA;
using JOB.WEB.Controllers;
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
    [AllowAnonymous]
    public class LoginController : System.Web.Http.ApiController
    {
        private Contexto ctx = new Contexto();

        public ApplicationSignInManager SignInManager => HttpContext.Current.GetOwinContext().Get<ApplicationSignInManager>();
        public ApplicationUserManager UserManager => HttpContext.Current.GetOwinContext().GetUserManager<ApplicationUserManager>();

        public async Task<HttpResponseMessage> Get(string Email, string Password)
        {
            var user = await UserManager.FindByNameAsync(Email);

            //200 - > login efetuado; 
            //403 -> usuario bloqueado; 
            //412 -> requer verificacao de email; 
            //400 -> falha no login
            var result = await SignInManager.PasswordSignInAsync(Email, Password, false, shouldLockout: true);
            switch (result)
            {
                case SignInStatus.Success:
                    var id = Guid.Parse(user.Id);
                    var usuario = ctx.Usuario.FirstOrDefault(w => w.ID_USUARIO == id);
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

        public async Task<HttpResponseMessage> Get(string Login, string Email, string Password)
        {
            var user = new ApplicationUser { UserName = Login, Email = Email, EmailConfirmed = true };
            var result = await UserManager.CreateAsync(user, Password);
            if (result.Succeeded)
            {
                return Request.CreateResponse(HttpStatusCode.OK, user.Id);
            }
            return Request.CreateResponse(HttpStatusCode.BadRequest, result);
        }        
    }
}
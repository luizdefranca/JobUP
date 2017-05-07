using System.Linq;
using Microsoft.AspNet.Identity.Owin;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;
using JOB.WEB.Models;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;

namespace JOB.WEB.ApiController
{
    [AllowAnonymous]
    public class LoginController : System.Web.Http.ApiController
    {
        public ApplicationSignInManager SignInManager => HttpContext.Current.GetOwinContext().Get<ApplicationSignInManager>();
        public ApplicationUserManager UserManager => HttpContext.Current.GetOwinContext().GetUserManager<ApplicationUserManager>();

        public async Task<HttpResponseMessage> Get(string Email, string Password)
        {
            var user = await UserManager.FindByNameAsync(Email);
            if (user != null)
            {
                if (!await UserManager.IsEmailConfirmedAsync(user.Id))
                {
                    return Request.CreateResponse(HttpStatusCode.BadRequest, new HttpError("You must have a confirmed email to log on. The confirmation token has been resent to your email account."));
                }
            }

            var result = await SignInManager.PasswordSignInAsync(Email, Password, false, shouldLockout: true);
            switch (result)
            {
                case SignInStatus.Success:
                    return Request.CreateResponse(HttpStatusCode.OK, "Success");

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
            var user = await UserManager.FindByNameAsync(Login);
            if (user != null)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, new HttpError("Login já existente"));
            }

            var user2 = await UserManager.FindByEmailAsync(Email);
            if (user2 != null)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, new HttpError("Email já existente"));
            }

            var db = new ApplicationDbContext();

            var userStore = new UserStore<ApplicationUser>(db);
            var userManager = new UserManager<ApplicationUser>(userStore);

            var newUser = new ApplicationUser()
            {
                UserName = Login,
                Email = Email
            };
            userManager.Create(newUser, Password);

            return Request.CreateResponse(HttpStatusCode.OK, newUser.Id);
        }
    }
}
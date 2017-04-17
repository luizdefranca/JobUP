using Microsoft.AspNet.Identity.Owin;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;

namespace JOB.WEB.ApiController
{
    [AllowAnonymous]
    public class AccountController : System.Web.Http.ApiController
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
                    return Request.CreateResponse(HttpStatusCode.BadRequest, new HttpError("LockedOut"));

                case SignInStatus.RequiresVerification:
                    return Request.CreateResponse(HttpStatusCode.BadRequest, new HttpError("RequiresVerification"));

                case SignInStatus.Failure:
                default:
                    return Request.CreateResponse(HttpStatusCode.BadRequest, new HttpError("Failure"));
            }
        }
    }
}
using System.Web.Http;
using JOB.WEB.App_Start;

namespace JOB.API
{
    public class WebApiApplication : System.Web.HttpApplication
    {
        protected void Application_Start()
        {
            GlobalConfiguration.Configure(WebApiConfig.Register);

            GlobalConfiguration.Configuration.IncludeErrorDetailPolicy = IncludeErrorDetailPolicy.Always;

            AutoMapperConfig.Register();
        }
    }
}
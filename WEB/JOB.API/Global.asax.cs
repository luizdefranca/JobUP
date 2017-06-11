using System.Web.Http;
using JOB.WEB.App_Start;
using System.Web.Mvc;

namespace JOB.API
{
    public class WebApiApplication : System.Web.HttpApplication
    {
        protected void Application_Start()
        {
            AreaRegistration.RegisterAllAreas();

            GlobalConfiguration.Configure(WebApiConfig.Register);

            GlobalConfiguration.Configuration.IncludeErrorDetailPolicy = IncludeErrorDetailPolicy.Always;

            AutoMapperConfig.Register();
        }
    }
}
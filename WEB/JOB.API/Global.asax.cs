using JOB.API.App_Start;
using System.Web.Http;
using System.Web.Mvc;

namespace JOB.API
{
    /// <summary>
    ///
    /// </summary>
    public class WebApiApplication : System.Web.HttpApplication
    {
        /// <summary>
        ///
        /// </summary>
        protected void Application_Start()
        {
            AreaRegistration.RegisterAllAreas();

            GlobalConfiguration.Configure(WebApiConfig.Register);

            GlobalConfiguration.Configuration.IncludeErrorDetailPolicy = IncludeErrorDetailPolicy.Always;

            AutoMapperConfig.Register();
        }
    }
}
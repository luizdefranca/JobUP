using JOB.WEB.Domain;
using System.Linq;
using System.Web.Mvc;

namespace JOB.WEB
{
    public class NavbarController : Controller
    {
        // GET: Navbar
        public ActionResult Index()
        {
            var data = new Data();
            return PartialView("_Navbar", data.navbarItems().ToList());
        }
    }
}
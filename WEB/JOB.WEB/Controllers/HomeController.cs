using AutoMapper;
using JOB.DATA;
using JOB.WEB.Models;
using Microsoft.AspNet.Identity;
using System;
using System.Data.Entity;
using System.Threading.Tasks;
using System.Web.Mvc;

namespace JOB.WEB.Controllers
{
    public class HomeController : Controller
    {
        private Contexto ctx = new Contexto();

        public async Task<ActionResult> Index()
        {
            if (User.Identity.IsAuthenticated)
            {
                Guid id = Guid.Parse(User.Identity.GetUserId());

                var domain = await ctx.Usuario.FirstOrDefaultAsync(w => w.ID_USUARIO == id);

                if (domain == null) //se ta nulo, é pq o usuario ainda nao cadastrou o perfil completo
                {
                    return RedirectToAction("Create", "Manage");
                }

                var model = Mapper.Map<UsuarioViewModel>(domain);

                return View(model);
            }
            else
            {
                return View();
            }
        }

        public ActionResult FlotCharts()
        {
            return View("FlotCharts");
        }

        public ActionResult MorrisCharts()
        {
            return View("MorrisCharts");
        }

        public ActionResult Tables()
        {
            return View("Tables");
        }

        public ActionResult Forms()
        {
            return View("Forms");
        }

        public ActionResult Panels()
        {
            return View("Panels");
        }

        public ActionResult Buttons()
        {
            return View("Buttons");
        }

        public ActionResult Notifications()
        {
            return View("Notifications");
        }

        public ActionResult Typography()
        {
            return View("Typography");
        }

        public ActionResult Icons()
        {
            return View("Icons");
        }

        public ActionResult Grid()
        {
            return View("Grid");
        }

        public ActionResult Blank()
        {
            return View("Blank");
        }

        public ActionResult Login()
        {
            return View("Login");
        }
    }
}
using AutoMapper;
using JOB.DATA;
using JOB.WEB.Models;
using Microsoft.AspNet.Identity;
using System;
using System.Data.Entity;
using System.Linq;
using System.Web.Mvc;

namespace JOB.WEB.Controllers
{
    public class HomeController : Controller
    {
        private Contexto ctx = new Contexto();

        public ActionResult Index()
        {
            if (User.Identity.IsAuthenticated)
            {
                Guid id = Guid.Parse(User.Identity.GetUserId());

                var domain = ctx.Usuario.FirstOrDefault(w => w.ID_USUARIO == id);

                if (domain == null) //se ta nulo, é pq o usuario ainda nao cadastrou o perfil completo
                {
                    return RedirectToAction("Create", "Manage");
                }

                var model = new HomeViewModel();

                model.APROVADO = domain.APROVADO;
                model.ATIVO = domain.ATIVO;
                model.QTD_PROFISSIONAIS = ctx.PerfilProfissional.Count();
                model.QTD_SERVICOS = ctx.Servico.Count(w => w.PUBLICO);

                return View(model);
            }
            else
            {
                return View();
            }
        }
    }
}
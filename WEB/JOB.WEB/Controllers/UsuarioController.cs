using AutoMapper;
using JOB.DATA;
using JOB.WEB.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web.Mvc;

namespace JOB.WEB.Controllers
{
    [Authorize]
    public class UsuarioController : Controller
    {
        private Contexto ctx = new Contexto();

        public UsuarioController()
        {
        }

        public UsuarioController(Contexto ctx)
        {
            this.ctx = ctx;
        }

        // GET: Usuario
        public ActionResult Index()
        {
            var domain = ctx.Usuario.ToList();

            var model = Mapper.Map<List<UsuarioViewModel_VW>>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)

            return View(model);
        }

        // GET: Usuario/Details/5
        public ActionResult DtAprovar(Guid id)
        {
            var domain = ctx.Usuario.First(w => w.ID_USUARIO == id);

            var model = Mapper.Map<UsuarioViewModel>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)

            return View(model);
        }

        public ActionResult Ativar(Guid id)
        {
            var domain = ctx.Usuario.First(w => w.ID_USUARIO == id);

            domain.Ativar();
            ctx.Entry(domain).State = EntityState.Modified;
            ctx.SaveChanges();

            return RedirectToAction("Index");
        }

        public ActionResult Bloquear(Guid id)
        {
            var domain = ctx.Usuario.First(w => w.ID_USUARIO == id);

            domain.Bloquear();
            ctx.Entry(domain).State = EntityState.Modified;
            ctx.SaveChanges();

            return RedirectToAction("Index");
        }

        public ActionResult Desbloquear(Guid id)
        {
            var domain = ctx.Usuario.First(w => w.ID_USUARIO == id);

            domain.Desbloquear();
            ctx.Entry(domain).State = EntityState.Modified;
            ctx.SaveChanges();

            return RedirectToAction("Index");
        }

        public ActionResult Aprovar(Guid id)
        {
            var domain = ctx.Usuario.First(w => w.ID_USUARIO == id);

            domain.Aprovar();
            ctx.Entry(domain).State = EntityState.Modified;
            ctx.SaveChanges();

            return RedirectToAction("Index");
        }
    }
}
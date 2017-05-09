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
                .ToListAsync();

            var model = Mapper.Map<List<UsuarioViewModel>>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)

            return View(model);
        }

        // GET: Usuario/Details/5
        public async Task<ActionResult> DtAprovar(Guid id)
        {
            var domain = await ctx.Usuario
                //.Include(i => i.CONTATO)
                //.Include(i => i.ENDERECO)
                .FirstAsync(w => w.ID_USUARIO == id);

            var model = Mapper.Map<UsuarioViewModel>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)

            return View(model);
        }

        {

            domain.Ativar();
            ctx.Entry(domain).State = EntityState.Modified;

            return RedirectToAction("Index");
        }

        {

            domain.Bloquear();
            ctx.Entry(domain).State = EntityState.Modified;

            return RedirectToAction("Index");
        }

        {

            domain.Desbloquear();
            ctx.Entry(domain).State = EntityState.Modified;

            return RedirectToAction("Index");
        }

        {

            domain.Aprovar();
            ctx.Entry(domain).State = EntityState.Modified;

            return RedirectToAction("Index");
        }
    }
}
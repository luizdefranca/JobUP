using AutoMapper;
using JOB.DATA;
using JOB.WEB.Extensions;
using JOB.WEB.Helper;
using JOB.WEB.Models;
using Microsoft.AspNet.Identity;
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
        private Guid id => User.Identity.GetId();

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

            var model = Mapper.Map<List<UsuarioViewModel_VW>>(domain);

            return View(model);
        }

        // GET: Usuario/Details/5
        public ActionResult DtAprovar(Guid id)
        {
            var domain = ctx.Usuario.First(w => w.ID_USUARIO == id);

            var model = Mapper.Map<UsuarioViewModel>(domain);

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

        public ActionResult AtivarDestaque(Guid id)
        {
            var domain = ctx.Usuario.First(w => w.ID_USUARIO == id);

            domain.AtivarDestaque();
            ctx.Entry(domain).State = EntityState.Modified;
            MoedaHelper.Movimentar(id, -800, "PERFIL COM DESTAQUE ATIVADO");
            ctx.SaveChanges();

            return RedirectToAction("Index", "Home");
        }

        public ActionResult IndexServico()
        {
            var domain = ctx.Servico.Where(w => w.ID_USUARIO == id).ToList();

            var lstModel = Mapper.Map<List<ServicoViewModel_api>>(domain);

            foreach (var model in lstModel)
            {
                model.NOME = ctx.Usuario.First(f => f.ID_USUARIO == model.ID_USUARIO).NOME;
                model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE).DESCRICAO;

                if (model.ID_SUB_ESPECIALIDADE != null)
                {
                    model.DESC_SUB_ESPECIALIDADE = ctx.SubEspecialidade.First(f => f.ID_SUB_ESPECIALIDADE == model.ID_SUB_ESPECIALIDADE).DESCRICAO;
                }
            }

            return View(lstModel);
        }

        public ActionResult Destaque()
        {
            Guid idUsuario = Guid.Parse(User.Identity.GetUserId());

            var domain = ctx.Usuario.First(w => w.ID_USUARIO == idUsuario);

            var model = Mapper.Map<UsuarioViewModel>(domain);

            return View(model);
        }
    }
}
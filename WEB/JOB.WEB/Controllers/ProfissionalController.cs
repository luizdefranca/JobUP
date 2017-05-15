using AutoMapper;
using JOB.DATA;
using JOB.DATA.Domain;
using JOB.WEB.Extensions;
using JOB.WEB.Models;
using JOB.WEB.Validation;
using Microsoft.AspNet.Identity;
using System;
using System.Collections.Generic;
using System.Linq;

using System.Web.Mvc;

namespace JOB.WEB.Controllers
{
    public class ProfissionalController : Controller
    {
        private Contexto ctx = new Contexto();
        private Guid id => User.Identity.GetId();

        private Guid idUsuario => Guid.Parse(User.Identity.GetUserId());

        // GET: Profissional
        public ActionResult Index(int idEspecialidade)
        {
            //var lstDominio = ctx.PerfilProfissional.Where(f => f.APROVADO == true).ToList();
            var lstDominio = ctx.PerfilProfissional.Where(f => f.ID_ESPECIALIDADE == idEspecialidade).ToList();

            var lstModel = Mapper.Map<List<ProfissionalViewModel>>(lstDominio);

            foreach (var model in lstModel)
            {
                model.NOME = ctx.Usuario.First(f => f.ID_USUARIO == model.ID_USUARIO).NOME;
                model.DT_NASCTO = ctx.Usuario.First(f => f.ID_USUARIO == model.ID_USUARIO).DT_NASCIMENTO;
                model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE)
                    .DESCRICAO;
            }

            return View(lstModel);
        }

        public ActionResult IndexPessoal()
        {
            //var lstDominio = ctx.PerfilProfissional.Where(f => f.APROVADO == true).ToList();
            var lstDominio = ctx.PerfilProfissional.Where(w => w.ID_USUARIO == idUsuario).ToList();

            var lstModel = Mapper.Map<List<ProfissionalViewModel>>(lstDominio);

            foreach (var model in lstModel)
            {
                //model.NOME = ctx.Usuario.First(f => f.ID_USUARIO == model.ID_USUARIO).NOME;
                //model.DT_NASCTO = ctx.Usuario.First(f => f.ID_USUARIO == model.ID_USUARIO).DT_NASCIMENTO;
                model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE).DESCRICAO;
            }

            return View(lstModel);
        }

        // GET: Profissional/Details/5
        public ActionResult Details(Guid id)
        {
            var Dominio = ctx.PerfilProfissional.First(f => f.ID_USUARIO == id);

            var model = Mapper.Map<ProfissionalViewModel>(Dominio); //converte a classe original para o viewmodel (que é reconhecida pela view)

            model.NOME = ctx.Usuario.First(f => f.ID_USUARIO == model.ID_USUARIO).NOME;
            model.DT_NASCTO = ctx.Usuario.First(f => f.ID_USUARIO == model.ID_USUARIO).DT_NASCIMENTO;
            model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE).DESCRICAO;

            return View(model);
        }

        // GET: Profissional/Create
        public ActionResult Create()
        {
            var domain = ctx.Usuario.FirstOrDefault(w => w.ID_USUARIO == id);

            if (domain == null) //se ta nulo, é pq o usuario ainda nao cadastrou o perfil completo
            {
                return RedirectToAction("Create", "Manage");
            }

            if (!domain.APROVADO) return RedirectToAction("Index", "Home");

            var cadprof = new CadastroProfissionalViewModel();
            cadprof.ESPECIALIDADES = ctx.Especialidade.ToList();
            return View(cadprof);
        }

        // POST: Profissional/Create
        [HttpPost]
        public ActionResult Create(CadastroProfissionalViewModel obj)
        {
            if (!ModelState.IsValid) return View(obj);

            try
            {
                //Guid id = Guid.Parse(User.Identity.GetUserId());

                var newobj = new PERFIL_PROFISSIONAL(idUsuario, obj.ID_ESPECIALIDADE, obj.RESUMO_CURRICULO);

                ctx.PerfilProfissional.Add(newobj);
                ctx.SaveChanges();
                return RedirectToAction("IndexPessoal");
            }
            catch (Exception ex)
            {
                ModelState.AddModelError("", ex.TratarMensagem());
                return View(obj);
            }
        }

        public ActionResult CriarJob(Guid id, int ID_ESPECIALIDADE)
        {
            var model = new JobViewModel();
            model.ID_USUARIO_PROFISSIONAL = id;
            model.ID_ESPECIALIDADE = ID_ESPECIALIDADE;

            return View(model);
        }

        //[HttpPost]
        //public ActionResult CriarJob(Guid id, JobViewModel obj)
        //{
        //    if (!ModelState.IsValid) return View(obj);

        //    try
        //    {
        //        Guid idCliente = Guid.Parse(User.Identity.GetUserId());
        //        //Guid IdProfissional = Guid.Parse(Request.QueryString["id"]);

        //        var newobj = new JOB.DATA.Domain.JOB(idCliente, obj.ID_USUARIO_PROFISSIONAL, obj.ID_ESPECIALIDADE, obj.DT_JOB, obj.TITULO, obj.OBSERVACOES, obj.VALOR_SUGERIDO);

        //        ctx.Job.Add(newobj);
        //        ctx.SaveChanges();
        //        return RedirectToAction("Index");
        //    }
        //    catch (Exception ex)
        //    {
        //        ModelState.AddModelError("", ex.TratarMensagem());
        //        return View(obj);
        //    }
        //}
    }
}
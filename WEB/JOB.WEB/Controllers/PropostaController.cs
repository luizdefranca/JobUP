using AutoMapper;
using JOB.DATA;
using JOB.DATA.Domain;
using JOB.WEB.Extensions;
using JOB.WEB.Models;
using Microsoft.AspNet.Identity;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web.Mvc;

namespace JOB.WEB.Controllers
{
    public class PropostaController : Controller
    {
        private Contexto ctx = new Contexto();
        private Guid idUsuarioLogado => User.Identity.GetId();

        // GET: Proposta
        public ActionResult Index()
        {
            var lstDominio = ctx.Especialidade.Include(i => i.PERFIS_PROFISSIONAIS).ToList();

            var lstModel = Mapper.Map<List<EspecialidadeViewModel>>(lstDominio);

            foreach (var item in lstModel)
            {
                item.QTD_PROFISSIONAIS = lstDominio.First(f => f.ID_ESPECIALIDADE == item.ID_ESPECIALIDADE).PERFIS_PROFISSIONAIS.Count();
            }

            return View(lstModel);
        }

        public ActionResult Create()
        {
            var model = new PropostaViewModel();

            return View(model);
        }

        // POST: Proposta/Create
        [HttpPost]
        public ActionResult Create(PropostaViewModel obj, Guid id)
        {
            var servico = ctx.Servico.Find(id);

            if (servico.PUBLICO == false)
            {
                var domain = ctx.Oferta.First(f => f.ID_SERVICO == id);

                domain.AceitarOferta();
                ctx.Entry(domain).State = EntityState.Modified;
            }

            var prop = new PROPOSTA_SERVICO(id, idUsuarioLogado, obj.VL_PROPOSTA, obj.JUSTIFICATIVA, obj.DURACAO_SERVICO, obj.VALOR_DURACAO_SERVICO);
            ctx.Proposta.Add(prop);

            ctx.SaveChanges();

            return RedirectToAction("../Home/Index");
        }

        public ActionResult ListarProposta()
        {
            var lstDominio = ctx.Proposta.Where(f => f.ID_USUARIO == idUsuarioLogado).ToList();

            var lstModel = Mapper.Map<List<PropostaViewModel>>(lstDominio);

            foreach (var model in lstModel)
            {
                model.DT_PROPOSTA = ctx.Proposta.First(f => f.ID_SERVICO == model.ID_SERVICO).DT_PROPOSTA;
                model.DURACAO_SERVICO = ctx.Proposta.First(f => f.ID_SERVICO == model.ID_SERVICO).DURACAO_SERVICO;
                model.VL_PROPOSTA = ctx.Proposta.First(f => f.ID_SERVICO == model.ID_SERVICO).VL_PROPOSTA;
            }

            return View(lstModel);
        }

        public ActionResult ListarPropostaCliente(Guid id)
        {
            var lstDominio = ctx.Proposta.Where(f => f.ID_SERVICO == id).ToList();

            var lstModel = Mapper.Map<List<PropostaViewModel>>(lstDominio);

            //foreach (var model in lstModel)
            //{
            //    model.DT_PROPOSTA = ctx.Proposta.First(f => f.ID_SERVICO == model.ID_SERVICO).DT_PROPOSTA;
            //    model.DURACAO_SERVICO = ctx.Proposta.First(f => f.ID_SERVICO == model.ID_SERVICO).DURACAO_SERVICO;
            //    model.VL_PROPOSTA = ctx.Proposta.First(f => f.ID_SERVICO == model.ID_SERVICO).VL_PROPOSTA;
            //    model.JUSTIFICATIVA = ctx.Proposta.First(f => f.ID_SERVICO == model.ID_SERVICO).JUSTIFICATIVA;
            //}

            return View(lstModel);
        }

        public ActionResult Details(Guid id)
        {
            var Dominio = ctx.Proposta.Include(i => i.SERVICO).First(f => f.ID_SERVICO == id);

            var model = Mapper.Map<PropostaViewModel>(Dominio);

            //model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE).DESCRICAO;
            //model.POSSUI_PROPOSTA = Dominio.PROPOSTAS.Any();

            model.DS_TITULO = ctx.Servico.First(f => f.ID_SERVICO == model.ID_SERVICO).DS_TITULO;
            model.VL_PROPOSTA = ctx.Proposta.First(f => f.ID_SERVICO == model.ID_SERVICO).VL_PROPOSTA;
            model.DT_PROPOSTA = ctx.Proposta.First(f => f.ID_SERVICO == model.ID_SERVICO).DT_PROPOSTA;
            model.JUSTIFICATIVA = ctx.Proposta.First(f => f.ID_SERVICO == model.ID_SERVICO).JUSTIFICATIVA;

            return View(model);
        }
    }
}
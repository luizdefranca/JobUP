using AutoMapper;
using JOB.DATA;
using JOB.DATA.Domain;
using JOB.WEB.Extensions;
using JOB.WEB.Models;
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
            var domain = ctx.Oferta.First(f => f.ID_SERVICO == id);

            domain.AceitarOferta();
            ctx.Entry(domain).State = EntityState.Modified;

            var prop = new PROPOSTA_SERVICO(id, User.Identity.GetId(), obj.VL_PROPOSTA, obj.JUSTIFICATIVA, obj.DURACAO_SERVICO, obj.VALOR_DURACAO_SERVICO);
            ctx.Proposta.Add(prop);

            ctx.SaveChanges();

            return RedirectToAction("Index");
        }
    }
}
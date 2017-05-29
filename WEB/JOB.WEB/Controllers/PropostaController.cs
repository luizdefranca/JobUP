using AutoMapper;
using JOB.DATA;
using JOB.DATA.Domain;
using JOB.HELPERS.Validation;
using JOB.WEB.Extensions;
using JOB.WEB.Helper;
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
            try
            {
                var servico = ctx.Servico.Find(id);

                if (servico.PUBLICO == false)
                {
                    var domain = ctx.Oferta.First(f => f.ID_SERVICO == id);

                    domain.AceitarOferta();
                    ctx.Entry(domain).State = EntityState.Modified;

                    MoedaHelper.Movimentar(ctx, idUsuarioLogado, -100, "PROPOSTA EFETUADA");
                }

                var prop = new PROPOSTA_SERVICO(id, idUsuarioLogado, obj.VL_PROPOSTA, obj.JUSTIFICATIVA, obj.DURACAO_SERVICO, obj.VALOR_DURACAO_SERVICO);
                ctx.Proposta.Add(prop);

                ctx.SaveChanges();

                return RedirectToAction("../Home/Index");
            }
            catch (Exception ex)
            {
                ModelState.AddModelError("", ex.TratarMensagem());
                return View(obj);
            }
        }

        public ActionResult ListarProposta(Guid id)
        {
            var lstDominio = ctx.Proposta.Where(f => f.ID_SERVICO == id).ToList();

            ViewBag.TITULO_SERVICO = ctx.Servico.Find(id).DS_TITULO;

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

        public ActionResult Aceitar(Guid idServico, Guid idUsuario)
        {
            var proposta = ctx.Proposta.Find(idServico, idUsuario);

            proposta.AceitarProposta();
            ctx.Entry(proposta).State = EntityState.Modified;

            ctx.SaveChanges();

            return RedirectToAction("ListarProposta", new { id = idServico });
        }

        public ActionResult FinalizarNegociacao(Guid ID_SERVICO, Guid ID_USUARIO)
        {
            var domain = ctx.Servico.Find(ID_SERVICO);
            var model = Mapper.Map<ServicoViewModel_full>(domain);

            return View(model);
        }
    }
}
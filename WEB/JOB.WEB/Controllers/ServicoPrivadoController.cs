using AutoMapper;
using JOB.DATA;
using JOB.DATA.Domain;
using JOB.HELPERS.Validation;
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
    public class ServicoPrivadoController : Controller
    {
        private Contexto ctx = new Contexto();
        private Guid id => User.Identity.GetId();

        // GET: Servico
        public ActionResult Index()
        {
            //busca as ofertas que nao foram negadas (ou seja, novas ou aceitas)
            var domain = ctx.Oferta.Where(w => w.ID_USUARIO == id & w.ACEITA != false).Select(s => s.SERVICO).ToList();

            var lstModel = Mapper.Map<List<ServicoViewModel_api>>(domain);

            foreach (var model in lstModel)
            {
                model.NOME = ctx.Usuario.First(f => f.ID_USUARIO == model.ID_USUARIO).NOME;
                model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE).DESCRICAO;

                if (model.ID_SUB_ESPECIALIDADE != null && model.ID_SUB_ESPECIALIDADE != 0)
                {
                    model.DESC_SUB_ESPECIALIDADE = ctx.SubEspecialidade.First(f => f.ID_SUB_ESPECIALIDADE == model.ID_SUB_ESPECIALIDADE).DESCRICAO;
                }
            }

            return View(lstModel);
        }

        // GET: Servico/Details/5
        public ActionResult Details(Guid id)
        {
            var Dominio = ctx.Servico.Include(i => i.PROPOSTAS).First(f => f.ID_SERVICO == id);

            var model = Mapper.Map<ServicoViewModel_full>(Dominio); //converte a classe original para o viewmodel (que é reconhecida pela view)

            model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE).DESCRICAO;
            model.POSSUI_PROPOSTA = Dominio.PROPOSTAS.Any();

            return View(model);
        }

        // GET: Servico/Create
        public ActionResult Create()
        {
            var domain = ctx.Usuario.FirstOrDefault(w => w.ID_USUARIO == id);

            if (domain == null) //se ta nulo, é pq o usuario ainda nao cadastrou o perfil completo
            {
                return RedirectToAction("Create", "Manage");
            }

            if (!domain.APROVADO) return RedirectToAction("Index", "Home");

            var model = new ServicoViewModel_full();

            var idEspecialidade = int.Parse(Request.QueryString["ID_ESPECIALIDADE"]);
            model.ID_ESPECIALIDADE = idEspecialidade;
            model.SUB_ESPECIALIDADES = ctx.SubEspecialidade.Where(w => w.ID_ESPECIALIDADE == idEspecialidade).ToList();
            return View(model);
        }

        // POST: Servico/Create
        [HttpPost]
        public ActionResult Create(ServicoViewModel_full obj, Guid idProfissional)
        {
            if (!ModelState.IsValid) return View(obj);

            try
            {
                Guid idUsuario = Guid.Parse(User.Identity.GetUserId());

                var objServico = new SERVICO(obj.ID_SERVICO, idUsuario, obj.ID_ESPECIALIDADE, obj.ID_SUB_ESPECIALIDADE, false, obj.DS_TITULO, obj.DS_OBSERVACOES, obj.VL_SUGERIDO, obj.TEMPO_SERVICO);
                ctx.Servico.Add(objServico);

                var objOferta = new OFERTA_SERVICO(obj.ID_SERVICO, idProfissional);
                ctx.Oferta.Add(objOferta);

                MoedaHelper.Movimentar(ctx, id, -100, "SERVIÇO PRIVADO OFERTADO");

                ctx.SaveChanges();
                return RedirectToAction("Index", "Profissional", new { idEspecialidade = obj.ID_ESPECIALIDADE });
            }
            catch (Exception ex)
            {
                ModelState.AddModelError("", ex.TratarMensagem());
                return View(obj);
            }
        }

        public ActionResult Aceitar(Guid id)
        {
            var domain = ctx.Proposta.FirstOrDefault(f => f.ID_SERVICO == id);

            var model = Mapper.Map<PropostaViewModel>(domain);

            return View(model);
        }

        [HttpPost]
        public ActionResult Aceitar(PropostaViewModel obj, Guid id)
        {
            var domain = ctx.Oferta.First(f => f.ID_SERVICO == id);

            domain.AceitarOferta();
            ctx.Entry(domain).State = EntityState.Modified;

            var proposta = new PROPOSTA_SERVICO(id, User.Identity.GetId(), obj.VL_PROPOSTA, obj.JUSTIFICATIVA, obj.DURACAO_SERVICO, obj.VALOR_DURACAO_SERVICO);
            ctx.Proposta.Add(proposta);

            ctx.SaveChanges();

            return RedirectToAction("Index");
        }

        public ActionResult Rejeitar(Guid id)
        {
            var domain = ctx.Oferta.First(f => f.ID_SERVICO == id);

            domain.RejeitarOferta();
            ctx.Entry(domain).State = EntityState.Modified;
            ctx.SaveChanges();

            return RedirectToAction("Index");
        }
    }
}
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
using System.Linq;
using System.Web.Mvc;

namespace JOB.WEB.Controllers
{
    public class ServicoPublicoController : Controller
    {
        private Contexto ctx = new Contexto();
        private Guid id => User.Identity.GetId();

        public ActionResult Index(int ID_ESPECIALIDADE)
        {
            Guid idUsuario = Guid.Parse(User.Identity.GetUserId());

            //var perfis = ctx.Usuario.Include(i => i.PERFIS_PROFISSIONAIS.Select(s => s.ESPECIALIDADE)).First(f => f.ID_USUARIO == idUsuario).PERFIS_PROFISSIONAIS.Where(w => w.APROVADO);
            //var perfis = ctx.Usuario.Include(i => i.PERFIS_PROFISSIONAIS.Select(s => s.ESPECIALIDADE)).First(f => f.ID_USUARIO == idUsuario).PERFIS_PROFISSIONAIS;
            //var lstEspecUsuario = perfis.Select(s => s.ESPECIALIDADE.ID_ESPECIALIDADE).ToList();

            var domain = ctx.Servico.Where(w => w.PUBLICO & w.ID_ESPECIALIDADE == ID_ESPECIALIDADE).ToList();

            var lstModel = Mapper.Map<List<ServicoViewModel_api>>(domain);

            foreach (var model in lstModel)
            {
                model.NOME = ctx.Usuario.First(f => f.ID_USUARIO == model.ID_USUARIO).NOME;
                model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE).DESCRICAO;
                model.TEMPO_SERVICO_DESC = EnumHelper.GetName(model.TEMPO_SERVICO);

                if (model.ID_SUB_ESPECIALIDADE != null)
                {
                    model.DESC_SUB_ESPECIALIDADE = ctx.SubEspecialidade.First(f => f.ID_SUB_ESPECIALIDADE == model.ID_SUB_ESPECIALIDADE).DESCRICAO;
                }
            }

            return View(lstModel);
        }

        // GET: Servico/Details/5
        public ActionResult Details(Guid id)
        {
            var Dominio = ctx.Servico.First(f => f.ID_SERVICO == id);

            var model = Mapper.Map<ServicoViewModel_full>(Dominio); //converte a classe original para o viewmodel (que é reconhecida pela view)

            model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE).DESCRICAO;
            if (model.ID_SUB_ESPECIALIDADE.HasValue) model.DESC_SUB_ESPECIALIDADE = ctx.SubEspecialidade.First(f => f.ID_SUB_ESPECIALIDADE == model.ID_SUB_ESPECIALIDADE).DESCRICAO;

            model.POSSUI_PROPOSTA = ctx.Proposta.Any(a => a.ID_SERVICO == id & a.ID_USUARIO == this.id);

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

            model.ESPECIALIDADES = ctx.Especialidade.ToList();

            var idEspecialidade = model.ESPECIALIDADES.First().ID_ESPECIALIDADE;
            model.ID_ESPECIALIDADE = idEspecialidade;
            model.SUB_ESPECIALIDADES = ctx.SubEspecialidade.Where(w => w.ID_ESPECIALIDADE == idEspecialidade).ToList();
            return View(model);
        }

        // POST: Servico/Create
        [HttpPost]
        public ActionResult Create(ServicoViewModel_full obj)
        {
            if (!ModelState.IsValid) return View(obj);

            try
            {
                //Guid idUsuario = Guid.Parse(User.Identity.GetUserId());
                var newobj = new SERVICO(obj.ID_SERVICO, id, obj.ID_ESPECIALIDADE, obj.ID_SUB_ESPECIALIDADE, true, obj.DS_TITULO, obj.DS_OBSERVACOES, obj.VL_SUGERIDO, obj.TEMPO_SERVICO);
                ctx.Servico.Add(newobj);

                //var objOferta = new OFERTA_SERVICO(obj.ID_SERVICO, id);
                //ctx.Oferta.Add(objOferta);

                MoedaHelper.Movimentar(ctx, id, -500, "SERVIÇO PÚBLICO OFERTADO");

                ctx.SaveChanges();
                return RedirectToAction("../Home/Index");
            }
            catch (Exception ex)
            {
                ModelState.AddModelError("", ex.TratarMensagem());
                return View(obj);
            }
        }
    }
}
using AutoMapper;
using JOB.DATA;
using JOB.DATA.Domain;
<<<<<<< HEAD
=======
using JOB.HELPERS.Validation;
>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae
using JOB.WEB.Extensions;
using JOB.WEB.Models;
using System;
using System.Collections.Generic;
using System.Linq;
<<<<<<< HEAD

=======
>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae
using System.Web.Mvc;

namespace JOB.WEB.Controllers
{
    public class ProfissionalController : Controller
    {
        private Contexto ctx = new Contexto();
<<<<<<< HEAD
        private Guid id => User.Identity.GetId();

        private Guid idUsuario => Guid.Parse(User.Identity.GetUserId());
=======
        private Guid idUsuario => User.Identity.GetId();
>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae

        // GET: Profissional
        public ActionResult Index(int idEspecialidade)
        {
            //var lstDominio = ctx.PerfilProfissional.Where(f => f.APROVADO == true).ToList();
            var lstDominio = ctx.PerfilProfissional
                .Include(i => i.USUARIO)
                .Where(f => f.ID_ESPECIALIDADE == idEspecialidade)
                .OrderByDescending(o => o.USUARIO.DT_ORDENACAO)
                .ToList();

            var lstModel = Mapper.Map<List<ProfissionalViewModel>>(lstDominio);

            foreach (var model in lstModel)
            {
                var usuario = ctx.Usuario.Find(model.ID_USUARIO);

                model.NOME = usuario.NOME;
                model.DT_NASCTO = usuario.DT_NASCIMENTO;
                model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE).DESCRICAO;
                model.PERFIL_DESTAQUE = usuario.PERFIL_DESTAQUE;
                model.DT_ORDENACAO = usuario.DT_ORDENACAO;
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
<<<<<<< HEAD
        public ActionResult Details(Guid id)
=======
        public ActionResult Details(Guid id, int idEspecialidade)
>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae
        {
            var Dominio = ctx.PerfilProfissional.First(f => f.ID_USUARIO == id);

            var model = Mapper.Map<ProfissionalViewModel>(Dominio); //converte a classe original para o viewmodel (que é reconhecida pela view)

            var usuario = ctx.Usuario
                .Include(i => i.PERFIS_PROFISSIONAIS)
                .Include(i => i.PROPOSTAS_SERVICO.Select(s => s.SERVICO))
                .First(F => F.ID_USUARIO == model.ID_USUARIO);

            model.NOME = usuario.NOME;
            model.DT_NASCTO = usuario.DT_NASCIMENTO;
            model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE).DESCRICAO;

            model.DT_INCLUSAO = usuario.DT_INCLUSAO;
            model.BAIRRO = usuario.BAIRRO;
            model.CIDADE = usuario.CIDADE;
            model.ESTADO = usuario.UF.ToString();

            model.OUTROS_PERFIS = Mapper.Map<List<ProfissionalViewModel>>(usuario.PERFIS_PROFISSIONAIS.Where(w => w.ID_ESPECIALIDADE != idEspecialidade));
            model.AVALIACOES = Mapper.Map<List<AvaliacaoViewModel>>(ctx.Avaliacao.Where(w => w.ID_USUARIO == model.ID_USUARIO & w.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE).ToList());

            var MEUS_SERVICOS = usuario.PROPOSTAS_SERVICO.Where(w => w.ACEITA == true).Select(s => s.SERVICO);

            if (MEUS_SERVICOS != null) model.SERVICOS.AddRange(Mapper.Map<List<ServicoViewModel_api>>(MEUS_SERVICOS));

            foreach (var item in model.OUTROS_PERFIS)
            {
                item.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == item.ID_ESPECIALIDADE).DESCRICAO;
            }

            return View(model);
        }

        // GET: Profissional/Create
        public ActionResult Create()
        {
<<<<<<< HEAD
            var domain = ctx.Usuario.FirstOrDefault(w => w.ID_USUARIO == id);
=======
            var domain = ctx.Usuario.FirstOrDefault(w => w.ID_USUARIO == idUsuario);
>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae

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
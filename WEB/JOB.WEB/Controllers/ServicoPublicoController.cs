using AutoMapper;
using JOB.DATA;
using JOB.DATA.Domain;
using JOB.WEB.Helper;
using JOB.WEB.Models;
using JOB.WEB.Validation;
using Microsoft.AspNet.Identity;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web.Mvc;

namespace JOB.WEB.Controllers
{
    public class ServicoPublicoController : Controller
    {
        private Contexto ctx = new Contexto();

        public ActionResult Index()
        {
            Guid idUsuario = Guid.Parse(User.Identity.GetUserId());

            //var perfis = ctx.Usuario.Include(i => i.PERFIS_PROFISSIONAIS.Select(s => s.ESPECIALIDADE)).First(f => f.ID_USUARIO == idUsuario).PERFIS_PROFISSIONAIS.Where(w => w.APROVADO);
            var perfis = ctx.Usuario.Include(i => i.PERFIS_PROFISSIONAIS.Select(s => s.ESPECIALIDADE)).First(f => f.ID_USUARIO == idUsuario).PERFIS_PROFISSIONAIS;
            var lstEspecUsuario = perfis.Select(s => s.ESPECIALIDADE.ID_ESPECIALIDADE).ToList();

            var domain = ctx.Servico.Where(w => w.PUBLICO & lstEspecUsuario.Contains(w.ID_ESPECIALIDADE)).ToList();

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

            return View(model);
        }

        // GET: Servico/Create
        public ActionResult Create()
        {
            var model = new ServicoViewModel_full();

            var idEspecialidade = int.Parse(Request.QueryString["ID_ESPECIALIDADE"]);
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
                Guid idUsuario = Guid.Parse(User.Identity.GetUserId());
                var newobj = new SERVICO(obj.ID_SERVICO, idUsuario, obj.ID_ESPECIALIDADE, obj.ID_SUB_ESPECIALIDADE, true, obj.DS_TITULO, obj.DS_OBSERVACOES, obj.VL_SUGERIDO, obj.TEMPO_SERVICO);

                ctx.Servico.Add(newobj);
                ctx.SaveChanges();
                return RedirectToAction("../Especialidade/Index2");
            }
            catch (Exception ex)
            {
                ModelState.AddModelError("", ex.TratarMensagem());
                return View(obj);
            }
        }

        // GET: Servico/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: Servico/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add update logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Servico/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: Servico/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add delete logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}
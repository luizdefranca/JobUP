using AutoMapper;
using JOB.DATA;
using JOB.DATA.Domain;
using JOB.WEB.Models;
using JOB.WEB.Validation;
using Microsoft.AspNet.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;

namespace JOB.WEB.Controllers
{
    public class ServicoPrivadoController : Controller
    {
        private Contexto ctx = new Contexto();

        // GET: Servico
        public ActionResult Index(Guid idUsuario)
        {
            var domain = ctx.Servico.Where(w => w.PUBLICO == false & w.ID_USUARIO == idUsuario).ToList();

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

        // GET: Servico/Details/5
        public ActionResult Details(int id)
        {
            return View();
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
        public ActionResult Create(ServicoViewModel_full obj, Guid id)
        {
            if (!ModelState.IsValid) return View(obj);

            try
            {
                Guid idUsuario = Guid.Parse(User.Identity.GetUserId());

                var objServico = new SERVICO(obj.ID_SERVICO, idUsuario, obj.ID_ESPECIALIDADE, obj.ID_SUB_ESPECIALIDADE, false, obj.DS_TITULO, obj.DS_OBSERVACOES, obj.VL_SUGERIDO, obj.TEMPO_SERVICO);
                ctx.Servico.Add(objServico);

                var objOferta = new OFERTA_SERVICO(obj.ID_SERVICO, id);
                ctx.Oferta.Add(objOferta);

                ctx.SaveChanges();
                return RedirectToAction("Index", "Profissional", new { idEspecialidade = obj.ID_ESPECIALIDADE });
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
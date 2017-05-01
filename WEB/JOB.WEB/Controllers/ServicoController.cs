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
    public class ServicoController : Controller
    {
        private Contexto ctx = new Contexto();

        // GET: Servico
        public ActionResult Index()
        {
            var domain = ctx.Servico.ToList();

            var lstModel = Mapper.Map<List<ServicoViewModel>>(domain);

            foreach (var model in lstModel)
            {
                model.NOME = ctx.Usuario.First(f => f.ID_USUARIO == model.ID_USUARIO).NOME;
                model.ESPECIALIDADES = ctx.Especialidade.ToList();
                model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE)
                    .DESCRICAO;

                if (model.ID_SUB_ESPECIALIDADE != null)
                {
                    model.DESC_SUB_ESPECIALIDADE = ctx.SubEspecialidade
                        .FirstOrDefault(f => f.ID_SUB_ESPECIALIDADE == model.ID_SUB_ESPECIALIDADE)
                        .DESCRICAO;
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
            var model = new ServicoViewModel();
            model.ESPECIALIDADES = ctx.Especialidade.ToList();
            return View(model);
        }

        // POST: Servico/Create
        [HttpPost]
        public ActionResult Create(ServicoViewModel obj)
        {
            if (!ModelState.IsValid) return View(obj);

            try
            {
                Guid idUsuario = Guid.Parse(User.Identity.GetUserId());

                var newobj = new SERVICO(idUsuario, obj.ID_ESPECIALIDADE, obj.ID_SUB_ESPECIALIDADE, obj.PUBLICO, obj.DS_TITULO, obj.DS_OBSERVACOES, obj.VL_SUGERIDO);

                ctx.Servico.Add(newobj);
                ctx.SaveChanges();
                return RedirectToAction("Index");
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
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using AutoMapper;
using JOB.DATA;
using JOB.WEB.Models;
using JOB.WEB.Validation;
using Microsoft.AspNet.Identity;
using JOB.DATA.Domain;

namespace JOB.WEB.Controllers
{
    public class ProfissionalController : Controller
    {
        Contexto ctx = new Contexto();

        // GET: Profissional
        public ActionResult Index()
        {
            //var lstDominio = ctx.PerfilProfissional.Where(f => f.APROVADO == true).ToList();
            var lstDominio = ctx.PerfilProfissional.ToList();

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

        // GET: Profissional/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: Profissional/Create
        public ActionResult Create()
        {
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
                Guid id = Guid.Parse(User.Identity.GetUserId());

                var newobj = new PERFIL_PROFISSIONAL(id, obj.ID_ESPECIALIDADE, obj.RESUMO_CURRICULO);

                ctx.PerfilProfissional.Add(newobj);
                ctx.SaveChanges();
                return RedirectToAction("Index");

            }
            catch (Exception ex)
            {
                ModelState.AddModelError("", ex.TratarMensagem());
                return View(obj);
            }
        }

        // GET: Profissional/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: Profissional/Edit/5
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

        // GET: Profissional/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: Profissional/Delete/5
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

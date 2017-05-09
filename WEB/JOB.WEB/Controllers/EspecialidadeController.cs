using AutoMapper;
using JOB.DATA;
using JOB.WEB.Models;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web.Mvc;

namespace JOB.WEB.Controllers
{
    public class EspecialidadeController : Controller
    {
        private Contexto ctx = new Contexto();

        // GET: Especialidade
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

        // GET: Especialidade
        public ActionResult Index2()
        {
            var lstDominio = ctx.Especialidade.Include(i => i.PERFIS_PROFISSIONAIS).ToList();

            var lstModel = Mapper.Map<List<EspecialidadeViewModel>>(lstDominio);

            return View(lstModel);
        }

        // GET: Especialidade
        public ActionResult Index3()
        {
            var lstDominio = ctx.Especialidade.Include(i => i.PERFIS_PROFISSIONAIS).ToList();

            var lstModel = Mapper.Map<List<EspecialidadeViewModel>>(lstDominio);

            foreach (var item in lstModel)
            {
                item.QTD_PROFISSIONAIS = lstDominio.First(f => f.ID_ESPECIALIDADE == item.ID_ESPECIALIDADE).PERFIS_PROFISSIONAIS.Count();
            }

            return View(lstModel);
        }

        // GET: Especialidade/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: Especialidade/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Especialidade/Create
        [HttpPost]
        public ActionResult Create(FormCollection collection)
        {
            try
            {
                // TODO: Add insert logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Especialidade/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: Especialidade/Edit/5
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

        // GET: Especialidade/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: Especialidade/Delete/5
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
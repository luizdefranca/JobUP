using AutoMapper;
using JOB.DATA;
using JOB.WEB.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace JOB.WEB.Controllers
{
    public class OfertaController : Controller
    {
        Contexto ctx = new Contexto();
        // GET: Oferta
        public ActionResult Index()
        {
            var lstDomain = ctx.Oferta.ToList();

            var lstModel = Mapper.Map<List<OfertaViewModel>>(lstDomain);

            return View(lstModel);
        }

        // GET: Oferta/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: Oferta/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Oferta/Create
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

        // GET: Oferta/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: Oferta/Edit/5
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

        // GET: Oferta/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: Oferta/Delete/5
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

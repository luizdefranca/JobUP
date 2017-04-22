using System;
using AutoMapper;
using JOB.DATA;
using JOB.WEB.Models;
using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;
using Microsoft.AspNet.Identity;

namespace JOB.WEB.Controllers
{
    public class JobController : Controller
    {
        private Contexto ctx = new Contexto();

        public Guid ID
        {
            get { return Guid.Parse(User.Identity.GetUserId()); }
        }

        // GET: Oferta
        public ActionResult Cliente()
        {
            var lstDomain = ctx.Job.Where(f => f.ID_USUARIO_CLIENTE == ID).ToList();

            var lstModel = Mapper.Map<List<JobViewModel>>(lstDomain);

            return View(lstModel);
        }

        public ActionResult Freela()
        {
            var lstDomain = ctx.Job.Where(f => f.ID_USUARIO_PROFISSIONAL == ID).ToList();

            var lstModel = Mapper.Map<List<JobViewModel>>(lstDomain);

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
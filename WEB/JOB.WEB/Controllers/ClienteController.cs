using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using AutoMapper;
using JOB.DATA;
using JOB.DATA.Domain;
using JOB.WEB.Models;
using System.Data.Entity;

namespace JOB.WEB.Controllers
{
    public class ClienteController : Controller
    {
        Contexto ctx = new Contexto();

        // GET: Cliente
        public ActionResult Index()
        {
            var lstDomain = ctx.Usuario.Include(x => x.CONTATO).ToList();
            //var lstDomain = ctx.Usuario.ToList();

            var lstModel = Mapper.Map<List<ClienteViewModel>>(lstDomain);

            //foreach (var usuarioModel in lstModel)
            //{
            //    //usuarioModel.TELEFONE = ctx.Contato.Last(f => f.ID_USUARIO == usuarioModel.ID_USUARIO).CELULAR.NrTelefone;
            //}

            return View(lstModel);
        }

        // GET: Cliente/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: Cliente/Create
        [HttpGet]
        public ActionResult Create()
        {
            return View();
        }

        // POST: Cliente/Create
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

        // GET: Cliente/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: Cliente/Edit/5
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

        // GET: Cliente/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: Cliente/Delete/5
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

using JOB.DATA;
using JOB.DATA.Domain;
using JOB.WEB.Models;
using Microsoft.AspNet.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace JOB.WEB.Controllers
{
    public class AvaliacaoController : Controller
    {

        Contexto ctx = new Contexto();
        
        public ActionResult Create()
        {
            var model = new AvaliacaoViewModel();
            return View(model);
        }

        // POST: Avaliacao/Create
        [HttpPost]
        public ActionResult Create(AvaliacaoViewModel obj, Guid idProfissional, int ID_ESPECIALIDADE, short vlNota)
        {
            try
            {
                Guid idCliente = Guid.Parse(User.Identity.GetUserId());

                var objAv = new AVALIACAO(idProfissional, ID_ESPECIALIDADE, idCliente, vlNota, obj.COMENTARIO);
                ctx.Avaliacao.Add(objAv);

                ctx.SaveChanges();
                return RedirectToAction("../Home/Index");
            }
            catch
            {
                return View(obj);
            }
        }
    }
}

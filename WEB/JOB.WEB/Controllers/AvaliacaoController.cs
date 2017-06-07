using AutoMapper;
using JOB.DATA;
using JOB.DATA.Domain;
using JOB.WEB.Models;
using Microsoft.AspNet.Identity;
using System;
using System.Web.Mvc;

namespace JOB.WEB.Controllers
{
    public class AvaliacaoController : Controller
    {
        private Contexto ctx = new Contexto();

        public ActionResult Create()
        {
            var model = new AvaliacaoViewModel();
            var idProfissional = Guid.Parse(Request.QueryString["idProfissional"]);
            var ID_ESPECIALIDADE = int.Parse(Request.QueryString["ID_ESPECIALIDADE"]);
            Guid idCliente = Guid.Parse(User.Identity.GetUserId());

            model = Mapper.Map<AvaliacaoViewModel>(ctx.Avaliacao.Find(idProfissional, ID_ESPECIALIDADE, idCliente));
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
                return RedirectToAction("Details", "Profissional", new { id = idProfissional, idEspecialidade = ID_ESPECIALIDADE });
            }
            catch
            {
                return View(obj);
            }
        }
    }
}
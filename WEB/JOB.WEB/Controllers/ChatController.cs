using JOB.DATA;
using JOB.WEB.Extensions;
using JOB.WEB.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web.Mvc;

namespace JOB.WEB.Controllers
{
    public class ChatController : Controller
    {
        private Contexto ctx = new Contexto();
        private Guid idUsuario => User.Identity.GetId();

        public ActionResult Index()
        {
            var model = new List<ListaChatVM>();

            var domain = ctx.Proposta.Include(i => i.SERVICO).Where(w => w.ACEITA.HasValue).Where(w => w.ACEITA.Value & w.ID_USUARIO == idUsuario).ToList();

            foreach (var item in domain)
            {
                model.Add(
                    new ListaChatVM()
                    {
                        ID_SERVICO = item.ID_SERVICO,
                        DESC_SERVICO = item.SERVICO.DS_TITULO,
                        QTD_NAO_LIDAS = ctx.Chat.Count(c => !c.LIDA & c.ID_SERVICO == item.ID_SERVICO)
                    });
            }

            return View(model);
        }
    }
}
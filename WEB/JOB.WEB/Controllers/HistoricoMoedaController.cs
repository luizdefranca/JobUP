using AutoMapper;
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
    public class HistoricoMoedaController : Controller
    {
        private Contexto ctx = new Contexto();
        private Guid id => User.Identity.GetId();

        // GET: HistoricoMoeda
        public ActionResult Index()
        {
            var domain = ctx.Usuario.Include(i => i.HISTORICOS_MOEDA).First(f => f.ID_USUARIO == id).HISTORICOS_MOEDA.OrderByDescending(o => o.DT_MOVIMENTACAO);

            var model = Mapper.Map<List<HistoricoMoedaVM>>(domain);

            return View(model);
        }
    }
}
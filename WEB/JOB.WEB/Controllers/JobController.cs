using System;
using AutoMapper;
using JOB.DATA;
using JOB.WEB.Models;
using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;
using Microsoft.AspNet.Identity;
using System.Threading.Tasks;
using System.Data.Entity;

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

            foreach (var item in lstModel)
            {
                item.nome = ctx.Usuario.First(f => f.ID_USUARIO == item.ID_USUARIO_PROFISSIONAL).NOME;
                item.especialidade = ctx.Especialidade.First(f=> f.ID_ESPECIALIDADE == item.ID_ESPECIALIDADE).DESCRICAO;
            }

            return View(lstModel);
        }

        public ActionResult Freela()
        {
            var lstDomain = ctx.Job.Where(f => f.ID_USUARIO_PROFISSIONAL == ID).ToList();

            var lstModel = Mapper.Map<List<JobViewModel>>(lstDomain);

            foreach (var item in lstModel)
            {
                item.nome = ctx.Usuario.First(f => f.ID_USUARIO == item.ID_USUARIO_PROFISSIONAL).NOME;
                item.especialidade = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == item.ID_ESPECIALIDADE).DESCRICAO;
            }

            return View(lstModel);
        }

        // GET: Oferta/Details/5
        public async Task<ActionResult> DetailsJobFreela(Int64 id)
        {
            var domain = await ctx.Job.FirstAsync(w => w.ID_JOB == id);                      

            var model = Mapper.Map<JobViewModel>(domain);            

            return View(model);
        }

        public async Task<ActionResult> Aceitar(Int64 id)
        {

            var domain = await ctx.Job.FirstAsync(f => f.ID_JOB == id);

            domain.Aceitar();
            ctx.Entry(domain).State = EntityState.Modified;
            await ctx.SaveChangesAsync();

            return RedirectToAction("Freela");
        }

        public async Task<ActionResult> DetailsJobCliente(Int64 id)
        {
            var domain = await ctx.Job.FirstAsync(w => w.ID_JOB == id);

            var model = Mapper.Map<JobViewModel>(domain);

            return View(model);
        }
    }
}
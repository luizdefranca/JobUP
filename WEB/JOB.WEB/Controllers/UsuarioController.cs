using AutoMapper;
using JOB.DATA;
using JOB.DATA.Domain;
using JOB.DATA.ValueObject;
using JOB.WEB.Models;
using JOB.WEB.Validation;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Threading.Tasks;
using System.Web.Mvc;

namespace JOB.WEB.Controllers
{
    [Authorize]
    public class UsuarioController : Controller
    {
        private Contexto ctx = new Contexto();

        public UsuarioController()
        {

        }

        public UsuarioController(Contexto ctx)
        {
            this.ctx = ctx;
        }

        // GET: Usuario
        public async Task<ActionResult> Index()
        {
            var domain = await ctx.Usuario.ToListAsync();
            var model = Mapper.Map<List<UsuarioViewModel>>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)

            return View(model);
        }

        // GET: Usuario/Details/5
        public async Task<ActionResult> Details(int id)
        {
            var domain = await ctx.Usuario.FirstAsync(w => w.ID_USUARIO == id);
            var model = Mapper.Map<UsuarioViewModel>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)

            return View(model);
        }

        // GET: Usuario/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Usuario/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Create(UsuarioViewModel obj)
        {
            if (!ModelState.IsValid) return View(obj);

            try
            {
                var newobj = new USUARIO(obj.NOME, new CPF(obj.CPF), new RG(obj.RgUF, obj.RgNR), obj.DT_NASCIMENTO);

                ctx.Usuario.Add(newobj);
                await ctx.SaveChangesAsync();

                return RedirectToAction("Index");
            }
            catch (Exception ex)
            {
                ModelState.AddModelError("", ex.TratarMensagem());
                return View(obj);
            }
        }

        // GET: Usuario/Edit/5
        public async Task<ActionResult> Edit(int id)
        {
            var domain = await ctx.Usuario.FirstAsync(w => w.ID_USUARIO == id);
            var model = Mapper.Map<UsuarioViewModel>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)

            return View(model);
        }

        // POST: Usuario/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> Edit(int id, UsuarioViewModel obj)
        {
            if (!ModelState.IsValid) return View(obj);

            try
            {
                var domain = await ctx.Usuario.FirstAsync(w => w.ID_USUARIO == id);

                domain.AtualizaDados(obj.NOME, new CPF(obj.CPF), new RG(obj.RgUF, obj.RgNR), obj.DT_NASCIMENTO);
                ctx.Entry(domain).State = EntityState.Modified;
                await ctx.SaveChangesAsync();

                return RedirectToAction("Index");
            }
            catch (Exception ex)
            {
                ModelState.AddModelError("", ex.TratarMensagem());
                return View(obj);
            }
        }

        // GET: Usuario/Delete/5
        public async Task<ActionResult> Delete(int id)
        {
            var domain = await ctx.Usuario.FirstAsync(w => w.ID_USUARIO == id);
            var model = Mapper.Map<UsuarioViewModel>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)

            return View(model);
        }

        // POST: Usuario/Delete/5
        [HttpPost]
        public async Task<ActionResult> Delete(int id, UsuarioViewModel obj)
        {
            try
            {
                var domain = await ctx.Usuario.FirstAsync(w => w.ID_USUARIO == id);

                ctx.Usuario.Remove(domain);
                await ctx.SaveChangesAsync();

                return RedirectToAction("Index");
            }
            catch (Exception ex)
            {
                ModelState.AddModelError("", ex.TratarMensagem());
                return View(obj);
            }
        }
    }
}
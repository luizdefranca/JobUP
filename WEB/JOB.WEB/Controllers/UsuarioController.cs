using AutoMapper;
using JOB.DATA;
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
            var domain = await ctx.Usuario
                .Include(i => i.CONTATO)
                .Include(i => i.ENDERECO)
                .ToListAsync();

            var model = Mapper.Map<List<UsuarioViewModel>>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)

            return View(model);
        }

        // GET: Usuario/Details/5
        public async Task<ActionResult> Aprovar(Guid id)
        {
            var domain = await ctx.Usuario
                .Include(i => i.CONTATO)
                .Include(i => i.ENDERECO).FirstAsync(w => w.ID_USUARIO == id);

            var model = Mapper.Map<UsuarioViewModel>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)

            return View(model);
        }

        // GET: Usuario/Delete/5
        public async Task<ActionResult> Delete(Guid id)
        {
            var domain = await ctx.Usuario.FirstAsync(w => w.ID_USUARIO == id);
            var model = Mapper.Map<UsuarioViewModel>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)

            return View(model);
        }

        // POST: Usuario/Delete/5
        [HttpPost]
        public async Task<ActionResult> Delete(Guid id, UsuarioViewModel obj)
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

        public async Task<ActionResult> Ativar(Guid id)
        {
            var domain = await ctx.Usuario.FirstAsync(w => w.ID_USUARIO == id);

            domain.Ativar();
            ctx.Entry(domain).State = EntityState.Modified;
            await ctx.SaveChangesAsync();

            return RedirectToAction("Index");
        }

        public async Task<ActionResult> Bloquear(Guid id)
        {
            var domain = await ctx.Usuario.FirstAsync(w => w.ID_USUARIO == id);

            domain.Bloquear();
            ctx.Entry(domain).State = EntityState.Modified;
            await ctx.SaveChangesAsync();

            return RedirectToAction("Index");
        }
    }
}
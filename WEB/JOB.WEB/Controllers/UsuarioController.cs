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
            var domain = await ctx.Usuario
                .Include(i => i.CONTATO)
                .Include(i => i.ENDERECO)
                .ToListAsync();

            var model = Mapper.Map<List<UsuarioViewModel>>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)

            return View(model);
        }

        // GET: Usuario/Details/5
        public async Task<ActionResult> Details(Guid id)
        {
            var domain = await ctx.Usuario
                .Include(i => i.CONTATO)
                .Include(i => i.ENDERECO).FirstAsync(w => w.ID_USUARIO == id);

            var model = Mapper.Map<UsuarioViewModel>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)

            return View(model);
        }

        //// GET: Usuario/Edit/5
        //public async Task<ActionResult> Edit(Guid id)
        //{
        //    var domain = await ctx.Usuario
        //        .Include(i => i.CONTATO)
        //        .Include(i => i.ENDERECO)
        //        .FirstAsync(w => w.ID_USUARIO == id);

        //    var model = Mapper.Map<UsuarioViewModel>(domain); //converte a classe original para o viewmodel (que é reconhecida pela view)

        //    return View(model);
        //}

        //// POST: Usuario/Edit/5
        //[HttpPost]
        //[ValidateAntiForgeryToken]
        //public async Task<ActionResult> Edit(Guid id, UsuarioViewModel obj)
        //{
        //    if (!ModelState.IsValid) return View(obj);

        //    try
        //    {
        //        var domain = await ctx.Usuario
        //            .Include(i => i.CONTATO)
        //            .Include(i => i.ENDERECO)
        //            .FirstAsync(w => w.ID_USUARIO == id);

        //        domain.AtualizaDados(obj.NOME, new CPF(obj.CPF), new RG(obj.RgUF, obj.RgNR), obj.DT_NASCIMENTO);

        //        if (domain.CONTATO == null)
        //            domain.AdicionarContato(new Telefone(obj.ContatoFIXO), new Telefone(obj.ContatoCELULAR), new Email(obj.ContatoEMAIL));
        //        else
        //            domain.CONTATO.AtualizarValor(new Telefone(obj.ContatoFIXO), new Telefone(obj.ContatoCELULAR));

        //        if (domain.ENDERECO == null)
        //            domain.AdicionarEndereco(obj.EnderecoUF, obj.EnderecoCEP, obj.EnderecoLOGRADOURO, obj.EnderecoCOMPLEMENTO, obj.EnderecoBAIRRO, obj.EnderecoCIDADE);
        //        else
        //            domain.ENDERECO.AtualizaValores(obj.EnderecoUF, obj.EnderecoCEP, obj.EnderecoLOGRADOURO, obj.EnderecoCOMPLEMENTO, obj.EnderecoBAIRRO, obj.EnderecoCIDADE);

        //        ctx.Entry(domain).State = EntityState.Modified;
        //        ctx.Entry(domain.CONTATO).State = EntityState.Modified;
        //        ctx.Entry(domain.ENDERECO).State = EntityState.Modified;

        //        await ctx.SaveChangesAsync();

        //        return RedirectToAction("Index");
        //    }
        //    catch (Exception ex)
        //    {
        //        ModelState.AddModelError("", ex.TratarMensagem());
        //        return View(obj);
        //    }
        //}

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

        public async Task<ActionResult> Aprovar(Guid id)
        {
            var domain = await ctx.Usuario.FirstAsync(w => w.ID_USUARIO == id);

            domain.Aprovar();
            ctx.Entry(domain).State = EntityState.Modified;
            await ctx.SaveChangesAsync();

            return RedirectToAction("Index");
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
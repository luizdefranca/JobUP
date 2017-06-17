using JOB.DATA;
using JOB.DATA.Domain;
using JOB.HELPERS.Validation;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;

namespace JOB.API.Controllers
{
    /// <summary>
    /// API de especialidades
    /// </summary>
    public class EspecialidadeController : ApiController
    {
        private Contexto ctx = new Contexto();

        /// <summary>
        /// Recupera todas as listas
        /// </summary>
        /// <returns></returns>
        [ResponseType(typeof(List<ESPECIALIDADE>))]
        public HttpResponseMessage Get()
        {
            var result = ctx.Especialidade.ToList();

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        /// <summary>
        /// Recupera uma determinada especialidade
        /// </summary>
        /// <param name="id">id da especialidade</param>
        /// <returns></returns>
        [ResponseType(typeof(ESPECIALIDADE))]
        public HttpResponseMessage Get(int id)
        {
            var result = ctx.Especialidade.FirstOrDefault(w => w.ID_ESPECIALIDADE == id);

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        /// <summary>
        /// Salva uma nova especialidade
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        [HttpPost]
        [ResponseType(typeof(HttpStatusCode))]
        public HttpResponseMessage Post(ESPECIALIDADE obj)
        {
            try
            {
                Validate(obj);
                if (!ModelState.IsValid)
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

                ctx.Especialidade.Add(obj);
                ctx.SaveChanges();

                return Request.CreateResponse(HttpStatusCode.Created);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
            }
        }

        /// <summary>
        /// Atualiza uma especialidade
        /// </summary>
        /// <param name="id">id da especialidade</param>
        /// <param name="obj"></param>
        /// <returns></returns>
        [ResponseType(typeof(HttpStatusCode))]
        public HttpResponseMessage Put(int id, ESPECIALIDADE obj)
        {
            try
            {
                var item = ctx.Especialidade.FirstOrDefault(w => w.ID_ESPECIALIDADE == id);

                item.AtualizaDados(obj.DESCRICAO, obj.EXIGIR_COMPROVACAO);
                ctx.Entry(item).State = EntityState.Modified;

                Validate(item);
                if (!ModelState.IsValid)
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

                ctx.SaveChanges();

                return Request.CreateResponse(HttpStatusCode.OK);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
            }
        }

        /// <summary>
        /// Deleta uma especialidade
        /// </summary>
        /// <param name="id">id da especialidade</param>
        public void Delete(int id)
        {
            var item = ctx.Especialidade.FirstOrDefault(w => w.ID_ESPECIALIDADE == id);

            ctx.Especialidade.Remove(item);
            ctx.SaveChanges();
        }
    }
}
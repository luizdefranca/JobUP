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
    /// API de usuarios
    /// </summary>
    public class UsuarioController : ApiController
    {
        private Contexto ctx = new Contexto();

        /// <summary>
        /// recupera todos os usuarios
        /// </summary>
        /// <returns></returns>
        [ResponseType(typeof(List<USUARIO>))]
        public HttpResponseMessage Get()
        {
            var result = ctx.Usuario.ToList();

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        /// <summary>
        /// recupera um determinado usuario
        /// </summary>
        /// <param name="id">id do usuario</param>
        /// <returns></returns>
        [ResponseType(typeof(USUARIO))]
        public HttpResponseMessage Get(Guid id)
        {
            var result = ctx.Usuario.FirstOrDefault(w => w.ID_USUARIO == id);

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        /// <summary>
        /// Insere um novo usuario
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        [HttpPost]
        [ResponseType(typeof(HttpStatusCode))]
        public HttpResponseMessage Post(USUARIO obj)
        {
            try
            {
                Validate(obj);
                if (!ModelState.IsValid)
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

                ctx.Usuario.Add(obj);
                ctx.SaveChanges();

                return Request.CreateResponse(HttpStatusCode.Created);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
            }
        }

        /// <summary>
        /// atualiza um determinado usuario
        /// </summary>
        /// <param name="id">id do usuario</param>
        /// <param name="obj"></param>
        /// <returns></returns>
        [ResponseType(typeof(HttpStatusCode))]
        public HttpResponseMessage Put(Guid id, USUARIO obj)
        {
            try
            {
                var item = ctx.Usuario.FirstOrDefault(w => w.ID_USUARIO == id);

                item.AtualizaDados(obj.NOME, obj.CPF, obj.RG, obj.DT_NASCIMENTO);
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
        /// deleta um determinado usuario
        /// </summary>
        /// <param name="id">id do usuario</param>
        public void Delete(Guid id)
        {
            try
            {
                var item = ctx.Usuario.FirstOrDefault(w => w.ID_USUARIO == id);

                ctx.Usuario.Remove(item);
                ctx.SaveChanges();
            }
            catch (Exception ex)
            {
                throw new Exception(ex.TratarMensagem());
            }
        }
    }
}
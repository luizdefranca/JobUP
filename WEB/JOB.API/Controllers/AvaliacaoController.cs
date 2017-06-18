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
    /// API de avaliacao de profissionais
    /// </summary>
    public class AvaliacaoController : ApiController
    {
        private Contexto ctx = new Contexto();

        /// <summary>
        /// Recupera todas as formacoes do usuario profissional
        /// </summary>
        /// <param name="idUsuario">id do usuario</param>
        /// <param name="idEspecialidade">id da especialidade</param>
        /// <returns></returns>
        [ResponseType(typeof(List<FORMACAO>))]
        public HttpResponseMessage Get(Guid idUsuario, int idEspecialidade)
        {
            var result = ctx.Formacao.Where(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade).ToList();

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        /// <summary>
        /// Recupera todas as avaliacoes do usuario, por especialidade e enviadas por um determinado cliente
        /// </summary>
        /// <param name="idUsuario">id do usuario</param>
        /// <param name="idEspecialidade">id da especialidade</param>
        /// <param name="idCliente">id do cliente</param>
        /// <returns></returns>
        [ResponseType(typeof(AVALIACAO))]
        public HttpResponseMessage Get(Guid idUsuario, int idEspecialidade, Guid idCliente)
        {
            var result = ctx.Avaliacao.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade & w.ID_CLIENTE == idCliente);

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        /// <summary>
        /// Salva uma nova avaliacao
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        [HttpPost]
        [ResponseType(typeof(HttpStatusCode))]
        public HttpResponseMessage Post(AVALIACAO obj)
        {
            try
            {
                Validate(obj);
                if (!ModelState.IsValid)
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

                ctx.Avaliacao.Add(obj);
                ctx.SaveChanges();

                return Request.CreateResponse(HttpStatusCode.Created);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
            }
        }

        /// <summary>
        /// Atualiza a avaliacao (nota e comentario)
        /// </summary>
        /// <param name="idUsuario">id do usuario</param>
        /// <param name="idEspecialidade">id da especialidade</param>
        /// <param name="idCliente">id do cliente</param>
        /// <param name="obj"></param>
        /// <returns></returns>
        [ResponseType(typeof(HttpStatusCode))]
        public HttpResponseMessage Put(Guid idUsuario, int idEspecialidade, Guid idCliente, AVALIACAO obj)
        {
            try
            {
                var item = ctx.Avaliacao.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade & w.ID_CLIENTE == idCliente);

                item.AtualizaDados(obj.NOTA, obj.COMENTARIO);
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
    }
}
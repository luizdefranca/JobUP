using JOB.DATA;
using JOB.DATA.Domain;
using JOB.HELPERS.Validation;
using JsonNet.PrivateSettersContractResolvers;
using Newtonsoft.Json;
using System;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

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
        /// <returns>retorna HttpStatusCode.OK = 200</returns>
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
        /// <returns>retorna HttpStatusCode.OK = 200</returns>
        public HttpResponseMessage Get(Guid idUsuario, int idEspecialidade, Guid idCliente)
        {
            var result = ctx.Avaliacao.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade & w.ID_CLIENTE == idCliente);

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        /// <summary>
        /// Salva uma nova avaliacao
        /// </summary>
        /// <param name="request">classe AVALIACAO</param>
        /// <returns>retorna HttpStatusCode.Created = 201</returns>
        [HttpPost]
        public HttpResponseMessage Post(HttpRequestMessage request)
        {
            try
            {
                var values = request.Content.ReadAsStringAsync().Result;

                var settings = new JsonSerializerSettings
                {
                    ContractResolver = new PrivateSetterContractResolver(),
                    ConstructorHandling = ConstructorHandling.AllowNonPublicDefaultConstructor
                };

                var obj = JsonConvert.DeserializeObject<AVALIACAO>(values, settings);

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
        /// <param name="request">classe AVALIACAO</param>
        /// <returns>retorna HttpStatusCode.OK = 200</returns>
        public HttpResponseMessage Put(Guid idUsuario, int idEspecialidade, Guid idCliente, HttpRequestMessage request)
        {
            try
            {
                var values = request.Content.ReadAsStringAsync().Result;

                var settings = new JsonSerializerSettings
                {
                    ContractResolver = new PrivateSetterContractResolver()
                };

                var item = ctx.Avaliacao.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade & w.ID_CLIENTE == idCliente);
                var obj = JsonConvert.DeserializeObject<AVALIACAO>(values, settings);

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
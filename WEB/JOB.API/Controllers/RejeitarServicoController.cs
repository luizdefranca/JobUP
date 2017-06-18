﻿using JOB.DATA;
using System;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;

namespace JOB.API.Controllers
{
    /// <summary>
    /// API de rejeicao de servico
    /// </summary>
    public class RejeitarServicoController : ApiController
    {
        private readonly Contexto ctx = new Contexto();

        /// <summary>
        /// Rejeita um determinado servico
        /// </summary>
        /// <param name="id">id do servico</param>
        /// <returns></returns>
        [ResponseType(typeof(HttpStatusCode))]
        public HttpResponseMessage Get(Guid id)
        {
            var domain = ctx.Oferta.First(f => f.ID_SERVICO == id);

            domain.RejeitarOferta();
            ctx.Entry(domain).State = EntityState.Modified;
            ctx.SaveChanges();

            return Request.CreateResponse(HttpStatusCode.OK);
        }
    }
}
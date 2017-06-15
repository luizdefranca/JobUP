using JOB.DATA;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace JOB.API.Controllers
{
    /// <summary>
    /// API de sub especialidades (aprofundamento de especialidade)
    /// </summary>
    public class SubEspecialidadeController : ApiController
    {
        private Contexto ctx = new Contexto();

        /// <summary>
        /// recupera todas as subespecialidades de uma determinada especialidade
        /// </summary>
        /// <param name="idEspecialidade">id da especialidade</param>
        /// <returns></returns>
        public HttpResponseMessage Get(int idEspecialidade)
        {
            var result = ctx.SubEspecialidade.Where(w => w.ID_ESPECIALIDADE == idEspecialidade).ToList();

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        /// <summary>
        /// recupera uma determinada subespecialidade
        /// </summary>
        /// <param name="idEspecialidade">id da especialidade</param>
        /// <param name="id">id da subespecialidade</param>
        /// <returns></returns>
        public HttpResponseMessage Get(int idEspecialidade, int id)
        {
            var result = ctx.SubEspecialidade.Where(w => w.ID_ESPECIALIDADE == idEspecialidade & w.ID_SUB_ESPECIALIDADE == id).ToList();

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }
    }
}
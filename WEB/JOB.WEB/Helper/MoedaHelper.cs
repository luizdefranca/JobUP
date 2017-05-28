using JOB.DATA;
using JOB.DATA.Domain;
using System;

namespace JOB.WEB.Helper
{
    public static class MoedaHelper
    {
        public static void Movimentar(Guid id, short valor, string motivo)
        {
            using (var ctx = new Contexto())
            {
                var historico = new HISTORICO_MOEDA(id, valor, motivo);
                ctx.Usuario.Find(id).MovimentarMoeda(valor);
                ctx.HistoricoMoeda.Add(historico);

                ctx.SaveChanges();
            }
        }
    }
}
using JOB.DATA;
using JOB.DATA.Domain;
using System;
using System.Data.Entity;

namespace JOB.WEB.Helper
{
    public static class MoedaHelper
    {
        public static void Movimentar(Contexto ctx, Guid idUsuario, short valor, string motivo)
        {
            var historico = new HISTORICO_MOEDA(idUsuario, valor, motivo);
            ctx.HistoricoMoeda.Add(historico);

            var usuario = ctx.Usuario.Find(idUsuario);
            usuario.MovimentarMoeda(valor);
            ctx.Entry(usuario).State = EntityState.Modified;

            try
            {
                ctx.SaveChanges();
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
    }
}
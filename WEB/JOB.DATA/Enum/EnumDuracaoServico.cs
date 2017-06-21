using System.ComponentModel.DataAnnotations;

namespace JOB.DATA.Enum
{
    public enum EnumDuracaoServico
    {
        /// <summary>
        /// Duracao do servico estimada em horas
        /// </summary>
        [Display(Name = "Hora(s)")]
        HORA = 0,

        /// <summary>
        /// Duracao do servico estimada em dias
        /// </summary>
        [Display(Name = "Dia(s)")]
        DIA = 1,

        /// <summary>
        /// Duracao do servico estimada em semanas
        /// </summary>
        [Display(Name = "Semana(s)")]
        SEMANA = 2,

        /// <summary>
        /// Duracao do servico estimada em meses
        /// </summary>
        [Display(Name = "Mês(es)")]
        MES = 3
    }
}
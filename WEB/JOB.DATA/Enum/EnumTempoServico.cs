using System.ComponentModel.DataAnnotations;

namespace JOB.DATA.Enum
{
    public enum EnumTempoServico
    {
        /// <summary>
        /// O quanto antes possível
        /// </summary>
        [Display(Name = "O quanto antes possível")]
        QUANTO_ANTES = 0,

        /// <summary>
        /// Nos próximos 30 dias
        /// </summary>
        [Display(Name = "Nos próximos 30 dias")]
        TRINTA_DIAS = 1,

        /// <summary>
        /// Nos próximos 3 meses
        /// </summary>
        [Display(Name = "Nos próximos 3 meses")]
        TRES_MESES = 2,

        /// <summary>
        /// Não tenho certeza
        /// </summary>
        [Display(Name = "Não tenho certeza")]
        NAO_SEI = 4
    }
}
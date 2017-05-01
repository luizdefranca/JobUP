using System.ComponentModel.DataAnnotations;

namespace JOB.DATA.Enum
{
    public enum EnumTempoServico
    {
        [Display(Name = "O quanto antes possível")]
        QUANTO_ANTES = 0,

        [Display(Name = "Nos próximos 30 dias")]
        TRINTA_DIAS = 1,

        [Display(Name = "Nos próximos 3 meses")]
        TRES_MESES = 2,

        [Display(Name = "Não tenho certeza")]
        NAO_SEI = 4
    }
}
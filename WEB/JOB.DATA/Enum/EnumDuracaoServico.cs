using System.ComponentModel.DataAnnotations;

namespace JOB.DATA.Enum
{
    public enum EnumDuracaoServico
    {
        [Display(Name = "Hora(s)")]
        HORA = 0,

        [Display(Name = "Dia(s)")]
        DIA = 1,

        [Display(Name = "Semana(s)")]
        SEMANA = 2,

        [Display(Name = "Mês(es)")]
        MES = 3
    }
}
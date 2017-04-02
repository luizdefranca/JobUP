using System.ComponentModel.DataAnnotations;

namespace JOB.DATA.Enum
{
    public enum EnumEstadoCivil
    {
        [Display(Name = "Casado")]
        CASADO,

        [Display(Name = "Solteiro")]
        SOLTEIRO,

        [Display(Name = "Outro")]
        OUTRO
    }
}
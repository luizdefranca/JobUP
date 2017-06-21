using System.ComponentModel.DataAnnotations;

namespace JOB.DATA.Enum
{
    public enum EnumEstadoCivil
    {
        /// <summary>
        /// Estado civil de casado
        /// </summary>
        [Display(Name = "Casado")]
        CASADO,

        /// <summary>
        /// Estado civil de solteiro
        /// </summary>
        [Display(Name = "Solteiro")]
        SOLTEIRO,

        /// <summary>
        /// Estado civil nao especificado
        /// </summary>
        [Display(Name = "Outro")]
        OUTRO
    }
}
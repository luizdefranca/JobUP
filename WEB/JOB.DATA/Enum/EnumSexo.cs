using System.ComponentModel.DataAnnotations;

namespace JOB.DATA.Enum
{
    /// <summary>
    /// TABELA TUSS - 43
    /// </summary>
    public enum EnumSexo
    {
        /// <summary>
        /// Masculino
        /// </summary>
        [Display(Name = "Masculino")]
        MASCULINO = 1,

        /// <summary>
        /// Feminino
        /// </summary>
        [Display(Name = "Feminino")]
        FEMININO = 3
    }
}
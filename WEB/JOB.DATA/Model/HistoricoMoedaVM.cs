using System;
using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Models
{
    public class HistoricoMoedaVM
    {
        [Key]
        public Guid ID_USUARIO { get; set; }

        [Key]
        [Display(Name = "Dt Movimentaçao")]
        public DateTime DT_MOVIMENTACAO { get; set; }

        /// <summary>
        /// Valor de moedas que foi movimentado
        /// </summary>
        [Display(Name = "Valor")]
        public Int16 VALOR_MOVIMENTADO { get; set; }

        /// <summary>
        /// Motivo da movimentação
        /// </summary>
        [Display(Name = "Motivo")]
        public string MOTIVO { get; set; }
    }
}
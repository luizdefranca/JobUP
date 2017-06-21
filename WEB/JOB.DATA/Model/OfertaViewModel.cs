using System;
using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Models
{
    public class OfertaViewModel
    {
        [Key]
        public Guid ID_SERVICO { get; set; }

        [Key]
        public Guid ID_USUARIO { get; set; }

        /// <summary>
        /// Data de cadastro do servico
        /// </summary>
        [Display(Name = "Data Oferta")]
        public DateTime DT_OFERTA { get; set; }

        /// <summary>
        /// Indica se o profissional aceitou ou recusou a oferta de serviço do cliente (se estiver nulo, ainda nao foi respondido)
        /// </summary>
        [Display(Name = "Aceitar?")]
        public bool? ACEITA { get; set; }

        [Display(Name = "Título")]
        public string DS_TITULO { get; set; }

        [Display(Name = "Observações")]
        public string DS_OBSERVACOES { get; set; }
    }
}
﻿using JOB.DATA.Enum;
using System;
using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Models
{
    public class UsuarioViewModel_VW
    {
        [Key]
        public Guid ID_USUARIO { get; set; }

        [Required]
        [Display(Name = "Nome")]
        public string NOME { get; set; }

        [Required]
        [Display(Name = "CPF")]
        public string CPF { get; set; }

        [Required]
        [Display(Name = "RG (UF)")]
        public EnumUF RgUF { get; set; }

        [Required]
        [Display(Name = "RG (NR)")]
        public string RgNR { get; set; }

        [Required]
        [Display(Name = "Dt. Nascimento")]
        [DataType(DataType.Date)]
        [Range(typeof(DateTime), "01/01/1753", "31/12/9999")]
        public DateTime DT_NASCIMENTO { get; set; }

        /// <summary>
        /// Indica se o cadastro do usuário foi aprovado pelo administrador
        /// </summary>
        [ScaffoldColumn(false)]
        [Display(Name = "Aprovado")]
        public bool APROVADO { get; set; }

        /// <summary>
        /// Indica se o cadastro do usuário esta ativo
        /// </summary>
        [ScaffoldColumn(false)]
        [Display(Name = "Ativo")]
        public bool ATIVO { get; set; }

        [ScaffoldColumn(false)]
        [Display(Name = "Bloqueado")]
        public bool BLOQUEADO { get; set; }

        [Display(Name = "Telefone Fixo")]
        public string ContatoFIXO { get; set; }

        [Required]
        [Display(Name = "Telefone Celular")]
        public string ContatoCELULAR { get; set; }
    }

    public class UsuarioViewModel
    {
        [Key]
        public Guid ID_USUARIO { get; set; }

        [Required]
        [Display(Name = "Nome *")]
        public string NOME { get; set; }

        [Required]
        [Display(Name = "CPF *")]
        public string CPF { get; set; }

        [Required]
        [Display(Name = "RG (UF) *")]
        public EnumUF RgUF { get; set; }

        [Required]
        [Display(Name = "RG (NR) *")]
        public string RgNR { get; set; }

        [Required]
        [Display(Name = "Dt. Nascimento *")]
        [DataType(DataType.Date)]
        [Range(typeof(DateTime), "01/01/1753", "31/12/9999")]
        [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
        public DateTime DT_NASCIMENTO { get; set; }

        [ScaffoldColumn(false)] //ScaffoldColumn(false) = não é gerado automaticamente nas views
        public DateTime DT_INCLUSAO { get; set; }

        [ScaffoldColumn(false)]
        public DateTime? DT_ALTERACAO { get; set; }

        [ScaffoldColumn(false)]
        public DateTime? DT_APROVACAO { get; set; }

        [ScaffoldColumn(false)]
        public DateTime DT_ORDENACAO { get; set; }

        /// <summary>
        /// Indica se o cadastro do usuário foi aprovado pelo administrador
        /// </summary>
        [ScaffoldColumn(false)]
        [Display(Name = "Aprovado")]
        public bool APROVADO { get; set; }

        /// <summary>
        /// Indica se o cadastro do usuário esta ativo
        /// </summary>
        [ScaffoldColumn(false)]
        [Display(Name = "Ativo")]
        public bool ATIVO { get; set; }

        [ScaffoldColumn(false)]
        [Display(Name = "Bloqueado")]
        public bool BLOQUEADO { get; set; }

        //[DataType(DataType.PhoneNumber)]
        [Display(Name = "Telefone Fixo")]
        public string ContatoFIXO { get; set; }

        [Required]
        //[DataType(DataType.PhoneNumber)]
        [Display(Name = "Telefone Celular *")]
        public string ContatoCELULAR { get; set; }

        ////[Required]
        //[DataType(DataType.EmailAddress)]
        //[Display(Name = "Email")]
        //public string ContatoEMAIL { get; set; }

        [Required]
        [Display(Name = "UF *")]
        public EnumUF UF { get; set; }

        [Required]
        [Display(Name = "CEP *")]
        public string CEP { get; set; }

        [Required]
        [Display(Name = "Logradouro *")]
        public string LOGRADOURO { get; set; }

        [Display(Name = "Complemento")]
        public string COMPLEMENTO { get; set; }

        [Required]
        [Display(Name = "Bairro *")]
        public string BAIRRO { get; set; }

        [Required]
        [Display(Name = "Cidade *")]
        public string CIDADE { get; set; }

        /// <summary>
        /// Destaque de perfil ativado
        /// </summary>
        [Display(Name = "Destaque Ativado?")]
        public bool PERFIL_DESTAQUE { get; set; }
    }
}
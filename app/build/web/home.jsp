<%@page  language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Financeiro - UFF - Principal</title>

    <!-- Fontes personalizadas para o template -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link  href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <!-- Css personalizado para esse template -->
    <link href="css/financeiro.css" rel="stylesheet">    
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">    
</head>

<body id="page-top">
    <!-- Container da página -->
    <div id="wrapper">
      <!-- Barra de menu lateral -->      
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
            <!-- Barra lateral - Logo Uff -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="home.jsp">
                <div class="sidebar-brand-icon">
                    <img src="img/logouffbranco.png" alt="Logo UFF" width="50" height="50">
                </div>
            </a>

            <!-- Divisor -->
            <hr class="sidebar-divider my-0">

            <!-- Item em negrito - Painel de controle -->
            <li class="nav-item active">
                <a class="nav-link" href="home.jsp">
                    <i class="fas fa-tasks"></i>
                    <span>Painel de Controle</span></a>
            </li>

            <!-- Divisor -->
            <hr class="sidebar-divider">

        <!-- Menu Admin -->
        <div id="gerenciamentoadmin" style="${adm}">
        <div class="sidebar-heading">
            Área Admin
        </div>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/CategoriaController?action=listcateogoria">
                    <i class="fas fa-sitemap"></i>
                    <span>Categorias</span></a>
            </li>

            <!-- Item de navegação - Funcionalidades do menu colapsado -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span>Gerenciar Acessos</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Gerenciamentos</h6>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/UsuarioController?action=listusuario?session=${sessionScope.usuarioLogado.nome}">Usuários</a>      
                       <a class="collapse-item" href="${pageContext.request.contextPath}/AdminController?action=listadmin">Administradores</a>                                
                    </div>
                </div>
            </li>
            <!-- Divisor -->
            <hr class="sidebar-divider">
        </div>
                    
            <!-- Cabeçalho -->
            <div id="menuusuario" style="${usu}">
                <div class="sidebar-heading">               
                    <span>Gerenciar Conta</span>
                </div>            

                <!-- Nav Item - Conta corrente -->
                <li id="btn-conta-corrente" class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ContaController?action=listconta&id=${sessionScope.idUsuarioLogado}&session=${sessionScope.usuarioLogado.nome}">
                        <i class="fas fa-university"></i>
                        <span>Conta Corrente</span></a>
                </li>

               <!-- Nav Item - Lançamentos -->
                <li class="nav-item">
                     <a class="nav-link" href="${pageContext.request.contextPath}/LancamentoController?action=listLancamento&id=${sessionScope.idUsuarioLogado}&session=${sessionScope.usuarioLogado.nome}">            
                        <i class="fas fa-file-invoice-dollar"></i>
                        <span>Lançamentos</span>
                     </a>
                </li>

                <!-- Divisor -->
                <hr class="sidebar-divider d-none d-md-block">
            </div> 

           <!-- Botao para recolher menu lateal -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- Fim da barra lateral -->
        <!-- Conteuúdo do container -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Conteúdo principal -->
            <div id="content">
                <!-- Barra superior -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                    <!-- Alternância da barra lateral (Barra superior) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Navegação da barra superior -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>  

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Item de navegação - Informações do usuário -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${sessionScope.usuarioLogado.nome}</span>
                                <img class="img-profile rounded-circle"
                                    src="img/undraw_profile.svg">
                            </a>
                           <!-- Dropdown - Informações do usuário -->
                           <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                           aria-labelledby="userDropdown">
                                <a class="dropdown-item" id="perfil" href="#" data-toggle="modal" data-target="#perfilModal">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Perfil
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item"  id="sair" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Sair
                                 </a>
                            </div>
                        </li>
                    </ul>
                </nav>
                <!-- Fim da barra superior  -->

                <!-- Inicio do conteúdo da página -->
                <div class="container-fluid">                   
                    <!-- Conteúdo da linha -->
                    <div class="row">                        
                        <div class="col-xl-4 col-md-6 mb-4">
                            <div class="card border-left-danger shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-danger text-uppercase mb-1">
                                                Débitos</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">R$ ${sessionScope.debitos}</td> </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas  fa-paper-plane fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-xl-4 col-md-6 mb-4">
                            <div class="card border-left-primary shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                Créditos</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">R$ ${sessionScope.creditos}</td> </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-4 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                Saldo Atual</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">R$ ${sessionScope.total}</td> </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-balance-scale fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Content Row -->

                    <div class="row" style="padding-bottom: 13%;">  
                        <!-- Project Card Example -->
                        <div class="col-xl-12 col-lg-5">
                            <div class="card shadow mb-4">
                                
                            </div>
                        </div>
                    </div>
            </div>
            <!-- Fim do conteúdo principal -->

            <!-- Footer -->
            <footer class="sticky-footer  bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; UFF - Universidade Federal Fluminense<br>Qualidade e Teste - 2022.2</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- Fim do container do conteúdo -->

    </div>
    <!-- Fim do Container principal da página -->
    
    <!--Botão para rolagem para o topo -->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>
    
    <!-- Modal de Logout-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Sair</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Tem certeza que deseja sair?</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/AutenticacaoController?deslogar=sim">Sair</a>
                </div>
            </div> 
        </div>
    </div>
    <!--Fim do Modal de Logout -->
    
   <!-- Modal de informações de usuários -->
   <div class="modal fade" id="perfilModal" tabindex="-1" role="dialog" aria-labelledby="perfilModalLabel"
   aria-hidden="true">
      <div class="modal-dialog" role="document">
          <div class="modal-content">
              <div class="modal-header">
                  <h5 class="modal-title" id="usuarioModalLabel">Informações do usuário logado</h5>
                  <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">×</span>
                  </button>
              </div>
              <!-- Corpo do modal -->
              <div class="modal-body">                    
                  <form class="row g-3 needs-validation" novalidate>
                      <div class="col-md-6">
                        <label for="nomeValicacao" class="form-label">Nome</label>
                        <label id="nomeValue" class="text-primary" for="nomeValicacao">${sessionScope.usuarioLogado.nome} - Id: ${sessionScope.usuarioLogado.id}</label>                          
                      </div>
                      <div class="col-md-5">
                        <label class="form-label">CPF</label>
                        <label id="cpfValue" class="text-primary" class="form-label">${sessionScope.cpf}</label>
                      </div>                       
                  </form>             
              </div>
              <div class="modal-footer">
                  <button class="btn btn-primary" type="submit" data-dismiss="modal">Ok</button>                               
              </div>                
          </div>
      </div>
  </div>
  <!--Fim modal de informações do usuario logado -->

    <!-- Javascript principal do bootstrap -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Javascript principal do plugin -->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Script personalizado para todas as páginas -->
    <script src="js/financeiro.js"></script>
</body>
</html>

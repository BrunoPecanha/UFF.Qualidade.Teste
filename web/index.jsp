<%@page  language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Gerenciamento Financeiro - UFF</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/index.css" rel="stylesheet" />
        <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">  
    </head>
    <body> 
        <!-- Header-->
        <header class="py-0">
            <div class="container px-lg-5">
                <div class="p-4 p-lg-5 bg-light rounded-3 text-center">
                    <div class="m-4 m-lg-5">
                        <h1 class="display-5 fw-bold">UFF - Gerenciamento Financeiro</h1>
                        <p class="fs-4">Uma aplicação em Java (servlet+jsp) que apoia um usuário 
                            no controle de lançamentos de créditos e débitos como uma conta corrente 
                            de um banco. Ao acessar a aplicação essa contará com uma área pública 
                            (onde informações sobre a aplicação estarão disponíveis) e uma área privada 
                            (onde as funções do sistema estão disponíveis)</p>
                        <a class="btn btn-primary btn-lg" href="login.jsp">Acessar à plataforma</a>
                    </div>
                </div>
            </div>
        </header>
        <!-- Page Content-->
        <span class="text-center">                        
            <h1 class="display-5 fw-bold">Papéis</h1>
            <hr>
        </span>
        <section class="pt-5">            
            <div class="container px-lg-5">
                <!-- Page Features-->
                <div class="row gx-lg-5">
                    <div class="col-lg-6 col-xxl-4 mb-5">
                        <div class="card bg-light border-0 h-100">
                            <div class="card-body text-center p-4 p-lg-5 pt-0 pt-lg-0">
                                <div class="feature bg-primary bg-gradient text-white rounded-3 mb-4 mt-n4"><i class="bi bi-collection"></i></div>
                                <h2 class="fs-4 fw-bold">Usuário</h2>
                                <p class="mb-0">Acessa a aplicação na sua área privada e interage com a sua conta corrente</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-xxl-4 mb-5">
                        <div class="card bg-light border-0 h-100">
                            <div class="card-body text-center p-4 p-lg-5 pt-0 pt-lg-0">
                                <div class="feature bg-primary bg-gradient text-white rounded-3 mb-4 mt-n4"><i class="bi bi-card-heading"></i></div>
                                <h2 class="fs-4 fw-bold">Público</h2>
                                <p class="mb-0">Acessa a aplicação na sua área pública e visualiza informações gerais sobre a aplicação</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-xxl-4 mb-5">
                        <div class="card bg-light border-0 h-100">
                            <div class="card-body text-center p-4 p-lg-5 pt-0 pt-lg-0">
                                <div class="feature bg-primary bg-gradient text-white rounded-3 mb-4 mt-n4"><i class="bi-patch-check"></i></div>
                                <h2 class="fs-4 fw-bold">Administrador</h2>
                                <p class="mb-0">Acessa a aplicação na sua área privada e administra os usuários</p>
                            </div>
                        </div>
                    </div>                  
                </div>
            </div>
        </section>
         <!-- Footer -->
            <footer class="sticky-footer  bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; UFF - Universidade Federal Fluminense<br>Qualidade e Teste - 2022.2</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->
        
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>       
    </body>
</html>
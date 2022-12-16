//
//  JlptListScreen.swift
//  
//
//  Created by surajsau on 2022/12/16.
//

import SwiftUI
import shared
import Utils
import DetailsFeature
import SearchFeature

struct JlptListScreen: View {

    @Environment(\.presentationMode) var presentationMode

    @ObservedObject var nativeVM = NativeViewModel(viewModel: JlptListViewModel.init())

    let level: Int

    var body: some View {
        ZStack {
            VStack {
                if let state = self.dispatcher.state {

                    if state.isLoading {
                        ProgressView()
                            .frame(maxWidth: .infinity, maxHeight: .infinity)
                    } else {
                        ScrollView(.vertical) {
                            LazyVStack(spacing: 14) {
                                ForEach(state.items, id: \.self) { result in
                                    NavigationLink {
                                        DetailsScreen(id: result.id, word: result.value)
                                    } label: {
                                        SearchResultRow(model: result)
                                    }
                                    .buttonStyle(.plain)
                                }
                            }
                            .padding(.vertical)
                        }

                        Spacer()
                    }
                }
            }
        }
        .onAppear {
            nativeVM.viewModel.doInitWith(level: Int32(self.level))
        }
        .navigationBarHidden(true)
        .navigationBarBackButtonHidden(true)
    }
}
